package cz.cvut.docta.core.presentation.component.container

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun DoctaBottomSheet(
    visible: Boolean,
    sheetState: ModalBottomSheetState,
    onDismissRequest: () -> Unit,
    backgroundColor: Color = DoctaColors.background,
    contentColor: Color = DoctaColors.onSurface,
    dragHandle: @Composable () -> Unit = {
//        BottomSheetHandle()
    },
    content: @Composable ColumnScope.() -> Unit
) {
    if (visible) {
        // TODO: Implement bottom sheet
//        BottomSheetScaffold {  }(
//            onDismissRequest = onDismissRequest,
//            sheetState = sheetState,
//            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
//            containerColor = backgroundColor,
//            contentColor = contentColor,
//            dragHandle = dragHandle
//        ) {
//            content()
//        }
    }
}